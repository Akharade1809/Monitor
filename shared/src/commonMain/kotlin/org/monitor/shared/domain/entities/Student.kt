package org.monitor.shared.domain.entities

public data class Student(
    public val studentId : String,
    public val studentFirstName : String,
    public val studentLastName : String,
    public val studentMobileNumber: String,
    public val studentWhatsappNumber: String,
    public val studentSchoolName: String,
    public val studentClass : Double
)