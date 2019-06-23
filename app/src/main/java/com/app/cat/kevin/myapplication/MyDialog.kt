package com.app.cat.kevin.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.widget.LinearLayout.VERTICAL
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.bottomsheets.gridItems
import com.afollestad.materialdialogs.bottomsheets.setPeekHeight
import com.afollestad.materialdialogs.datetime.datePicker
import com.afollestad.materialdialogs.list.customListAdapter
import es.dmoral.toasty.Toasty
import java.util.*

class MyDialog{
    companion object {
        val items = listOf(
                CustomAdapterModel(img = R.drawable.some_icon, txt = "One"),
                CustomAdapterModel(img = R.drawable.some_icon, txt = "Two"),
                CustomAdapterModel(img = R.drawable.some_icon, txt = "Three"),
                CustomAdapterModel(img = R.drawable.some_icon, txt = "Four")
        )

        fun dateDialog(context: Context) {
            MaterialDialog(context).show {
                datePicker(maxDate = eightTeenYears(), minDate = getMaxDate(), currentDate = getUserYear(), dateCallback = { dialog, datetime ->
                    Toasty.success(context, "Success!", Toast.LENGTH_SHORT, true).show()
                })
            }
        }

        @SuppressLint("WrongConstant")
        private fun setLinear(context: Context, imageClick: CustomAdapter.OnImageClick): RecyclerView.Adapter<*> {
            val customAdapter = CustomAdapter()
            val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false)
            layoutManager.isSmoothScrollbarEnabled = true
            customAdapter.setListView(items, context = context, onImageClick = imageClick)
            return customAdapter
        }

        fun bottomDialog(context: Context, imageClick: CustomAdapter.OnImageClick) {
            val dialog = MaterialDialog(context, BottomSheet(LayoutMode.WRAP_CONTENT)).show {
                title(R.string.app_name, null)

                customListAdapter(setLinear(context, imageClick))
            }

            dialog.setPeekHeight(res = R.dimen.watermark_height2)

        }

        private fun eightTeenYears(): Calendar {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.YEAR, -18)
            return calendar
        }

        private fun getMaxDate(): Calendar {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.YEAR, -120)
            return calendar
        }

        private fun getUserYear(): Calendar {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.YEAR, -22)
            return calendar
        }
    }

}