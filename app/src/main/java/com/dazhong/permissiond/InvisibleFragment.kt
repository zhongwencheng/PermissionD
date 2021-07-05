package com.dazhong.permissiond

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
typealias PermissionCall=(Boolean,List<String>)->Unit
class InvisibleFragment:Fragment() {
  private var callback :PermissionCall ?=null
  fun requestNow(cb:PermissionCall,vararg permission:String){
      callback = cb
      requestPermissions(permission,1)
  }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==1){
            val deniedList = ArrayList<String>()
            for ((index,result) in grantResults.withIndex()){
               if (result!=PackageManager.PERMISSION_GRANTED){
                   deniedList.add(permissions[index])
               }
            }
            val allGranted = deniedList.isEmpty()
            callback?.let { it(allGranted,deniedList) }
        }

    }
}
