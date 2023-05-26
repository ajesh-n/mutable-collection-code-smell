# Kotlin understanding immutability aspect in object modeling

Ever wondered when designing domain object models, why it's important to keep immutability in mind.
Let's take an example, we are building an application to manage events (meet-ups). We have domain models like given below

```
data class Contact(
val name: String,
val email: String,
val address: Address
)
```

```kotlin
data class Organizer(
val name: String,
val contact: Contact
)
```

```kotlin
data class Event(
val eventId: Int,
val eventName: String,
val eventDate: String,
val location: Address,
val attendees: ArrayList<Contact>,
val organizer: Organizer
)
```

May be due to our business requirements we expose a method from Event class to add an attendee like given below 

```kotlin
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
```

Consider a scenario where application creates two events

```kotlin
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
    eventDate = "2023-06-16",
    location = address,
    attendees = attendees,
    organizer = organizer,
)
```

Both event1 and event2 have two attendees
later we add one more attendee to event2

```kotlin
val contact3 = Contact("John Doe", "john.doe@example.com", address)

event1.addAttendee(contact3)
```

Here we will observe that contact3 automatically added as attendee to event2 also but our intention was to add only for event1.

### Conclusion

Usually while designing objects (~ being from java background) we use data structures without thinking about the mutability aspect.

java.util.ArrayList is a mutable collection and we can use kotlin.collections.List which provides a nice interface to give read only access. Because both event1 and event2 have references to the same ArrayList, any modifications will be applicable for event1 and event2.