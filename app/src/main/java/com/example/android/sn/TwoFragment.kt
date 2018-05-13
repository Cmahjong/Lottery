package com.example.android.sn


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.sn.adapter.Base1Adapter
import kotlinx.android.synthetic.main.fragment_two.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TwoFragment : Fragment() {
    val adapter: Base1Adapter by lazy {
        Base1Adapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycle_view2.layoutManager = LinearLayoutManager(activity)
        recycle_view2.adapter = adapter
        adapter.setNewData(ConstantData.data2)
        adapter.setOnItemClickListener { adapter, view, position ->
            WebViewActivity.launch(activity, ConstantData.data2[position].url
                    ?: "", ConstantData.data2[position].title ?: "")
        }
    }
}
