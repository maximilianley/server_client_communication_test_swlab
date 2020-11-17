import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class User {
    var id: Long = 0
    lateinit var username: String
    lateinit var password: String
    var role: String = "USER"
}