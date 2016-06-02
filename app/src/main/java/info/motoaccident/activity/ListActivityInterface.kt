package info.motoaccident.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import rx.Observable

interface ListActivityInterface {
    fun contentView(): View
    fun getContext(): Context
    fun getPermittedResources(): Observable<Pair<View, Int>>
    fun runActivity(activity: Class<*>, bundle: Bundle = Bundle.EMPTY)
}