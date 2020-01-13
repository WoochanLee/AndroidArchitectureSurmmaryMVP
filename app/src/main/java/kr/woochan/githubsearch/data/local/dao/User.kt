package kr.woochan.githubsearch.data.local.dao

import io.realm.RealmObject

open class User : RealmObject() {
    var name: String = ""
    var profileUrl: String = ""
}