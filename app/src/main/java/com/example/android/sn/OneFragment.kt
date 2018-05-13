package com.example.android.sn


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.sn.adapter.BaseAdapter
import kotlinx.android.synthetic.main.fragment_one.*


/**
 * A simple [Fragment] subclass.
 *
 */
class OneFragment : Fragment() {
    val adapter:BaseAdapter by lazy {
        BaseAdapter()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycle_view.layoutManager = LinearLayoutManager(activity)
        recycle_view.adapter= adapter
        adapter.setNewData(ConstantData.data1)
        adapter.setOnItemClickListener { adapter, view, position ->
            WebViewActivity.launch(activity,ConstantData.data1[position].url?:"",ConstantData.data1[position].title?:"")
        }
    }

}
