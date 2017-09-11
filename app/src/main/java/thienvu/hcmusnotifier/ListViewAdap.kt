package thienvu.hcmusnotifier

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView





/**
 * Created by thien on 05/09/2017.
 */
class ListViewAdap(var context: Context, var ntfArr: ArrayList<Notify>): BaseAdapter() {

    class ViewHolder(row: View){
        var tvTitle: TextView = row.findViewById(R.id.tv_tittle)
        //var tvURL: TextView = row.findViewById(R.id.tv_URL)
        var tvDate: TextView = row.findViewById(R.id.tv_date)
    }

    @SuppressLint("InflateParams")
    override fun getView(pos: Int, convertView: View?, p2: ViewGroup?): View {
        val view: View?
        val viewHolder: ViewHolder

        if (convertView == null){
            val layoutInfater: LayoutInflater = LayoutInflater.from(context)
            view = layoutInfater.inflate(R.layout.notify, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }

        val ntf: Notify = getItem(pos) as Notify
        viewHolder.tvTitle.text = ntf.tittle
        //viewHolder.tvURL.text = ntf.url
        viewHolder.tvDate.text = ntf.date

        return view as View
    }

    override fun getItem(p0: Int): Any {
        return ntfArr[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return ntfArr.size
    }
}