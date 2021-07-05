package com.dazhong.permissiond

import androidx.fragment.app.FragmentActivity

object PermissionD {
    private const val TAG = "InvisibleFragment"
    fun request(activity: FragmentActivity,vararg permissions:String,callBack:PermissionCall){
         val  fragmentManager = activity.supportFragmentManager
         val  existedFragment = fragmentManager.findFragmentByTag(TAG)
         val  fragment = if (existedFragment!=null){
              existedFragment as InvisibleFragment
        }else{
            val  invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        fragment.requestNow(callBack,*permissions)
    }
}