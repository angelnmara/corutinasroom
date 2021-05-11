package com.lamarrulla.examentecnicoandroid.ui.contact

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast

import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels

import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.lamarrulla.examentecnicoandroid.AppDatabase
import com.lamarrulla.examentecnicoandroid.R
import com.lamarrulla.examentecnicoandroid.api.DataSource
import com.lamarrulla.examentecnicoandroid.api.RepoImpl
import com.lamarrulla.examentecnicoandroid.data.Result
import com.lamarrulla.examentecnicoandroid.utils.VMFactory

/**
 * A fragment representing a list of Items.
 */
class ConcactFragment : Fragment() {

    private val TAG = javaClass.name
    private var columnCount = 1
    private val contactViewModel by activityViewModels<ContactViewModel>() { VMFactory(RepoImpl(DataSource(
        AppDatabase.getDatabase(requireContext().applicationContext)))) }
    private lateinit var progresBar:RelativeLayout
    private lateinit var svContact: SearchView
    private lateinit var rv:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact_list, container, false)
        progresBar = view.findViewById(R.id.progress_bar_contact)
        svContact = view.findViewById(R.id.svContacts)
        rv = view.findViewById(R.id.list_contact)

        /*svContact.setOnCloseListener(object : SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                Log.d(TAG, "onClose: close")
                contactViewModel.getName("")
                return false
            }
        })*/
        svContact.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i(TAG,"Llego al querysubmit")
                contactViewModel.getName(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if(newText==""){
                    contactViewModel.getName(newText!!)
                }
                return true
            }
        })

        // Set the adapter
        if (rv is RecyclerView) {
            with(rv) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                //adapter = MyConcactRecyclerViewAdapter(PlaceholderContent.ITEMS)
                SetupObserverContactListByNameRoom(rv)
            }
        }
        return view
    }

    private fun SetupObserverContactListByNameRoom(recyclerView: RecyclerView){
        contactViewModel.fetchContactListByNameRoom.observe(viewLifecycleOwner, Observer {
                result->
            when(result){
                is Result.Success->{
                    progresBar.visibility = View.GONE
                    if(result.data.isEmpty()){
                        Toast.makeText(requireContext(), "Ningúna coincidencia", Toast.LENGTH_LONG).show()
                        SetupoObserverContactList(recyclerView)
                    }else{
                        recyclerView.adapter = MyConcactRecyclerViewAdapter(result.data, requireContext()){
                                item->
                            Log.d(TAG, "SetupObserverContact: " + item.name)
                            val action = ConcactFragmentDirections.actionConcactFragmentToContactDetailFragment(item)
                            view?.findNavController()?.navigate(action)
                        }
                    }
                }
                is Result.Error->{
                    progresBar.visibility = View.GONE
                }
                is Result.Loading->{
                    progresBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun SetupoObserverContactList(recyclerView: RecyclerView){
        contactViewModel.fetchContactList.observe(viewLifecycleOwner, Observer {
                result->
            when(result){
                is Result.Success->{
                    progresBar.visibility = View.GONE
                    contactViewModel.guardarContactList(result.data)
                    recyclerView.adapter = MyConcactRecyclerViewAdapter(result.data, requireContext()){
                            item->
                        Log.d(TAG, "SetupObserverContact: " + item.name)
                        val action = ConcactFragmentDirections.actionConcactFragmentToContactDetailFragment(item)
                        view?.findNavController()?.navigate(action)
                    }
                }
                is Result.Error->{
                    progresBar.visibility = View.GONE
                }
                is Result.Loading->{
                    progresBar.visibility = View.VISIBLE
                }
            }
        })
    }

    /*private fun SetupObserverContactListRoom(recyclerView: RecyclerView){
        contactViewModel.fetchContactListRoom.observe(viewLifecycleOwner, Observer {
                result->
            when(result){
                is Result.Success->{
                    progresBar.visibility = View.GONE
                    if(result.data.isEmpty()){
                        SetupoObserverContactList(recyclerView)
                    }else{
                        recyclerView.adapter = MyConcactRecyclerViewAdapter(result.data, requireContext()){
                                item->
                            Log.d(TAG, "SetupObserverContact: " + item.name)
                            val action = ConcactFragmentDirections.actionConcactFragmentToContactDetailFragment(item)
                            view?.findNavController()?.navigate(action)
                        }
                    }
                }
                is Result.Error->{
                    progresBar.visibility = View.GONE
                }
                is Result.Loading->{
                    progresBar.visibility = View.VISIBLE
                }
            }
        })
    }*/

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ConcactFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}