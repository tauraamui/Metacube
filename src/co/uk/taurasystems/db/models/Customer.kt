package co.uk.taurasystems.db.models

class Customer : Model {

    var ID: Long = -1
    var firstName = "First name"
    var surname = "Surname"
    var phoneNumber = ""
    var addressFirstLine = ""

    constructor() {
    }

    constructor(ID: Long, firstName: String, surname: String, phoneNumber: String, addressFirstLine: String) {
        this.ID = ID
        this.firstName = firstName
        this.surname = surname
        this.phoneNumber = phoneNumber
        this.addressFirstLine = addressFirstLine
    }

    constructor(firstName: String, surname: String, phoneNumber: String, addressFirstLine: String) {
        this.firstName = firstName
        this.surname = surname
        this.phoneNumber = phoneNumber
        this.addressFirstLine = addressFirstLine
    }
}
