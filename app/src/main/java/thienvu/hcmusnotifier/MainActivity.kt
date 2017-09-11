package thienvu.hcmusnotifier

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import android.content.Intent

class MainActivity : AppCompatActivity() {

    val url = "http://web.hcmus.edu.vn/index.php?option=com_content&task=blogcategory&id=0&Itemid=1322"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ReadData().execute(url).get()

    }

    @SuppressLint("StaticFieldLeak")
    inner class ReadData: AsyncTask<String, Void, ArrayList<Notify>>(){
        override fun doInBackground(vararg p0: String?): ArrayList<Notify> {
            val ntfArr: ArrayList<Notify> = ArrayList()
            val doc: Document = Jsoup.connect(url).get()
            val ntfElements = doc.select("li")
            val linkTitleElements =  ntfElements.select("a")
            val dateElements = ntfElements.select("span")

            for (i in 0..ntfElements.size-1){
                val tit = linkTitleElements[i].text()
                val link = linkTitleElements[i].attr("href")
                val date = dateElements[i].text()
                val ntf = Notify(tit, link, date)

                ntfArr.add(ntf)
            }

            return ntfArr
        }

        override fun onPostExecute(result: ArrayList<Notify>?) {
            super.onPostExecute(result)

            lv_notify.adapter = ListViewAdap(this@MainActivity, result as ArrayList<Notify>)

            lv_notify.setOnItemClickListener({ _, _, i, _ ->
                val intent = Intent(this@MainActivity, WebViewActivity::class.java)
                intent.putExtra("url", result[i].url)
                startActivity(intent)
            })
        }
    }
}
