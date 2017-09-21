package np.com.ngimasherpa.tech_companies_in_nepal.ui.adapter

import android.media.Image
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import np.com.ngimasherpa.tech_companies_in_nepal.R
import np.com.ngimasherpa.tech_companies_in_nepal.model.Company
import np.com.ngimasherpa.tech_companies_in_nepal.preview.LinkPreviewCallback
import np.com.ngimasherpa.tech_companies_in_nepal.preview.PreviewUtils
import np.com.ngimasherpa.tech_companies_in_nepal.preview.SourceContent
import np.com.ngimasherpa.tech_companies_in_nepal.utils.DialogUtils
import np.com.ngimasherpa.tech_companies_in_nepal.utils.Utils

/**
 * Created by ngima on 9/19/17.
 */
class CompanyListAdapter : RecyclerView.Adapter<CompanyListAdapter.ViewHolder>() {

    private var companyList = listOf<Company>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int)
            = ViewHolder(LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_company, parent, false))

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var company = companyList[position]
        holder?.textCompanyName?.setText(company.name)
        holder?.textCompanyLocation?.setText(company.location)
    }

    override fun getItemCount() = companyList.size

    fun setCompanyList(companyList: List<Company>) {
        this.companyList = companyList
        notifyDataSetChanged()
    }

    inner class ViewHolder : RecyclerView.ViewHolder {
        var textCompanyName = itemView?.findViewById<TextView>(R.id.text_company_name)
        var textCompanyLocation = itemView?.findViewById<TextView>(R.id.text_company_location)


        constructor(view: View?) : super(view) {
            itemView?.setOnClickListener({
                DialogUtils.viewDetails(view?.context, companyList[adapterPosition])
            })
        }
    }

}