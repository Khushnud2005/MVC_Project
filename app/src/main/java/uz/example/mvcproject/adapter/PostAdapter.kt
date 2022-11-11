package uz.example.mvcproject.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SwipeLayout
import uz.example.mvcproject.R
import uz.example.mvcproject.activity.DetailsActivity
import uz.example.mvcproject.activity.EditActivity
import uz.example.mvcproject.activity.MainActivity
import uz.example.mvcproject.model.Post
import java.lang.String

class PostAdapter(val activity:MainActivity,val items:ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post_list,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post: Post = items[position]

        val swpipe: SwipeLayout = holder.sl_swipe
        val left:LinearLayout = holder.linear_left
        val right:LinearLayout = holder.linear_right
        val ll_item:LinearLayout = holder.ll_item
        swpipe.showMode = SwipeLayout.ShowMode.PullOut
        swpipe.addDrag(SwipeLayout.DragEdge.Left,left)
        swpipe.addDrag(SwipeLayout.DragEdge.Right,right)
        val delete: TextView = holder.tv_delete
        val edit: TextView = holder.tv_edit

        val tv_title = holder.tv_title
        val tv_body = holder.tv_body
        tv_title.setText(post.title.toUpperCase())
        tv_body.setText(post.body)

        swpipe.setOnLongClickListener {
            activity.deletePostDialog(post)
            false
        }
        delete.setOnClickListener {
            activity.deletePostDialog(post)
        }
        edit.setOnClickListener {
            val intent = Intent(activity.baseContext, EditActivity::class.java)
            intent.putExtra("id", String.valueOf(post.id))
            intent.putExtra("user_id", String.valueOf(post.userId))
            intent.putExtra("title", post.title)
            intent.putExtra("body", post.body)
            activity.startActivity(intent)
        }
        ll_item.setOnClickListener {
            val intent = Intent(activity.baseContext, DetailsActivity::class.java)
            intent.putExtra("id", String.valueOf(post.id))
            intent.putExtra("user_id", String.valueOf(post.userId))
            intent.putExtra("title", post.title)
            intent.putExtra("body", post.body)
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sl_swipe: SwipeLayout
        var tv_title: TextView
        var tv_body: TextView
        var tv_delete: TextView
        var tv_edit: TextView
        var linear_left: LinearLayout
        var linear_right: LinearLayout
        var ll_item: LinearLayout

        init {
            sl_swipe = itemView.findViewById(R.id.sl_swipe)
            linear_left = itemView.findViewById(R.id.ll_linear_left)
            linear_right = itemView.findViewById(R.id.ll_linear_right)
            ll_item = itemView.findViewById(R.id.ll_item)
            tv_title = itemView.findViewById(R.id.tv_title)
            tv_body = itemView.findViewById(R.id.tv_body)
            tv_delete = itemView.findViewById(R.id.tv_delete)
            tv_edit = itemView.findViewById(R.id.tv_edit)
        }
    }
}