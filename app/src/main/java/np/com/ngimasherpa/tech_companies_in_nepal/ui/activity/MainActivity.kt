package np.com.ngimasherpa.tech_companies_in_nepal.ui.activity

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import com.miguelcatalan.materialsearchview.MaterialSearchView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import np.com.ngimasherpa.tech_companies_in_nepal.BuildConfig
import np.com.ngimasherpa.tech_companies_in_nepal.R
import np.com.ngimasherpa.tech_companies_in_nepal.api.ApiRequest
import np.com.ngimasherpa.tech_companies_in_nepal.base.BaseActivity
import np.com.ngimasherpa.tech_companies_in_nepal.exception.NetworkConnectionException
import np.com.ngimasherpa.tech_companies_in_nepal.model.Company
import np.com.ngimasherpa.tech_companies_in_nepal.preview.GetFavicon
import np.com.ngimasherpa.tech_companies_in_nepal.ui.adapter.CompanyListAdapter
import np.com.ngimasherpa.tech_companies_in_nepal.utils.AppPreferences
import np.com.ngimasherpa.tech_companies_in_nepal.utils.DialogUtils
import np.com.ngimasherpa.tech_companies_in_nepal.utils.Utils

class MainActivity : BaseActivity() {
    override var layout = R.layout.activity_main
    lateinit var appPreferences: AppPreferences;
    var adapter = CompanyListAdapter()
    private var companyList = listOf<Company>()

    override fun init() {
        appPreferences = AppPreferences(this)

        setSupportActionBar(toolbar)

        setTitle("TCN")
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter

        render(appPreferences.retrieveCompanies() as List<Company>)

        ApiRequest.getInstance(this, BuildConfig.BASE_URL).companies
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    appPreferences.saveCompanies(it)
                    render(it)
                }, this::onError)

        search_view.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search_view.dismissSuggestions()
                search(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchClear(newText)
                return true
            }
        })
    }

    private fun searchClear(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            adapter.setCompanyList(companyList)
        }
    }

    private fun search(query: String?) {
        if (query == null || query.isEmpty()) {
            adapter.setCompanyList(mutableListOf())
        }
        var list = mutableListOf<Company>()

        for (company in companyList) {
            if (company.name.containsIgnoreCase(query) || company.location.containsIgnoreCase(query) ||
                    company.description.containsIgnoreCase(query)) {
                list.add(company)
            }
        }

        adapter.setCompanyList(list)
    }

    private fun onError(it: Throwable) {
        if (it is NetworkConnectionException) {
            DialogUtils.showError(this, "Couldn't connect to the server")
        }
        it.printStackTrace()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        var item = menu?.findItem(R.id.action_search)
        search_view.setMenuItem(item)
        return true
    }

    private fun render(companyList: List<Company>) {
        initSuggestion(companyList)
        this.companyList = companyList
        adapter.setCompanyList(companyList)
    }

    private fun initSuggestion(companyList: List<Company>) {
        var sb = StringBuilder()

        for (company in companyList) {
            sb.append(company.name + " ")
            sb.append(company.location + " ")
            sb.append(company.description + " ")
        }

        search_view.setSuggestions(Utils.getUniqueList(sb.toString()).toTypedArray())
    }

    companion object {

        fun setupIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}


fun String.containsIgnoreCase(searchStr: String?): Boolean {
    if (this == null || searchStr == null) return false

    val length = searchStr.length
    if (length == 0)
        return true

    for (i in this.length - length downTo 0) {
        if (this.regionMatches(i, searchStr, 0, length, ignoreCase = true))
            return true
    }
    return false
}
