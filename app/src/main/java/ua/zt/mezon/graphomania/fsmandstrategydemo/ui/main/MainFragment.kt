package  ua.zt.mezon.graphomania.fsmandstrategydemo.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.don11995.log.SimpleLog
import kotlinx.android.synthetic.main.main_fragment.*
import ua.zt.mezon.graphomania.fsmandstrategydemo.R
import ua.zt.mezon.graphomania.fsmandstrategydemo.datasources.ItemData

class MainFragment : Fragment(), MainFragmentViewStatesRenderContract {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        viewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        viewModel.mViewState.observe(viewLifecycleOwner, {
            it?.let { render(it) }
        })
        viewModel.initialize(this)
        viewModel.startLoadData()
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun showIni() {
        SimpleLog.d("showInitState() called")
        greetingTextView.visibility = View.VISIBLE
        greetingTextView.text = "Prepare to download"
        progressBar.isIndeterminate =false
        progressBar.visibility = View.GONE
        errorTextView.visibility = View.GONE
        progressBar.max = 100
        progressBar.progress = 0
    }

    override fun showLoadCounterPercentData(percent: Int) {
        SimpleLog.d("showLoadProgress() called with: percent = $percent")
        greetingTextView.visibility = View.GONE
        progressBar.progress = percent
        progressBar.visibility = View.VISIBLE
        errorTextView.visibility = View.GONE
    }

    override fun showLoadError(error: String?) {
        SimpleLog.d("showError() called with: error = $error")
        greetingTextView.visibility = View.GONE
        progressBar.visibility = View.GONE
        errorTextView.visibility = View.VISIBLE
        errorTextView.text = error
    }

    override fun showListEmpty() {
        SimpleLog.d("showEmptyState() called")
        greetingTextView.visibility = View.VISIBLE
        greetingTextView.text = "No Data"
        progressBar.visibility = View.GONE
        errorTextView.visibility = View.GONE
    }

    override fun showListShow(listItems: ArrayList<ItemData>) {
        SimpleLog.d("showList() called with: listItems = $listItems")
        var tmpstr = "\n"
        for (item in listItems) {
            tmpstr = tmpstr + item.title + "\n"
        }
        greetingTextView.visibility = View.VISIBLE
        greetingTextView.text = tmpstr
        progressBar.visibility = View.GONE
        errorTextView.visibility = View.GONE

    }


}