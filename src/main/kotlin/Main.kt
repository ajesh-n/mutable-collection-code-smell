data class Address(
    val street: String,
    val city: String,
    val postalCode: String
)

data class Contact(
    val name: String,
    val email: String,
    val address: Address
)

data class Event(
    val eventId: Int,
    val eventName: String,
    val eventDate: String,
    val location: Address,
    val attendees: ArrayList<Contact>,
    val organizer: Organizer
) {
    fun addAttendee(contact: Contact) {
        attendees.add(contact)
    }
}

data class Organizer(
    val name: String,
    val contact: Contact
)

fun main() {
    val address = Address("123 Main Street", "Cityville", "12345")
    val organizerAddress = Address("456 Broadway", "Townsville", "54321")
    val organizerContact = Contact("John Doe", "john.doe@example.com", organizerAddress)
    val organizer = Organizer("Event Management Inc.", organizerContact)

    val contact1 = Contact("Jane Smith", "jane.smith@example.com", address)
    val contact2 = Contact("Robert Johnson", "robert.johnson@example.com", address)

    val attendees = arrayListOf(contact1, contact2)

    val event1 = Event(
        eventId = 1,
        eventName = "Conference 1",
        eventDate = "2023-06-15",
        location = address,
        attendees = attendees,
        organizer = organizer
    )

    val event2 = Event(
        eventId = 2,
        eventName = "Conference 2",
        eventDate = "2023-06-15",
        location = address,
        attendees = attendees,
        organizer = organizer,
    )

    val contact3 = Contact("John Doe", "john.doe@example.com", address)
    event1.addAttendee(contact3)

    println(event1.attendees)
    println(event2.attendees)
}
