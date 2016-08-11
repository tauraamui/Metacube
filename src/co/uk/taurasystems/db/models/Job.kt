package co.uk.taurasystems.db.models

class Job {

    enum class Priority {
        LOW,
        MEDIUM,
        HIGH,
        CRITICAL
    }

    var ID: Long = 0
    var customerID: Long = 0
    var date: String? = null
    var time: String? = null
    var priority: Priority? = null

    var deviceName: String? = null
    var isBagOrCase: Boolean = false
    var description: String? = null
    var price: Float = 0.toFloat()

    constructor() {
    }

    constructor(date: String, time: String, priority: Priority) {
        this.date = date
        this.time = time
        this.priority = priority
    }
}
