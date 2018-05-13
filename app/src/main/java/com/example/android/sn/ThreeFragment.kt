package com.example.android.sn


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.sn.adapter.BaseAdapter
import kotlinx.android.synthetic.main.fragment_three.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ThreeFragment : Fragment() {
    val adapter: BaseAdapter by lazy {
        BaseAdapter()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_three, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycle_view3.layoutManager = LinearLayoutManager(activity)
        recycle_view3.adapter= adapter
        adapter.setNewData(ConstantData.data3)
        adapter.setOnItemClickListener { adapter, view, position ->
            WebViewActivity.launch(activity,ConstantData.data3[position].url?:"",ConstantData.data3[position].title?:"")
        }
    }
}
