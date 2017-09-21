package np.com.ngimasherpa.tech_companies_in_nepal.api

import io.reactivex.Observable
import np.com.ngimasherpa.tech_companies_in_nepal.model.Company
import retrofit2.http.GET

/**
 * Created by ngima on 9/19/17.
 */
interface ApiService {

    @GET(Api.PATH_COMPANIES)
    fun getCompanies(): Observable<List<Company>>
}

object Api {
    const val PATH_COMPANIES = "companies"
}