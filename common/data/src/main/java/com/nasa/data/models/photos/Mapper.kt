package com.nasa.data.models.photos

import com.nasa.domain.models.Camera
import com.nasa.domain.models.Photo
import com.nasa.domain.models.Rover

fun RoverDto.toRover() = Rover(
    id = id, landingDate = landingDate, launchDate = launchDate, name = name, status = status
)

fun PhotoDto.toPhoto() = Photo(
    camera = camera?.toCamera(),
    earthDate = earthDate,
    id = id,
    imgSrc = imgSrc,
    rover = rover?.toRover(),
    sol = sol
)

fun CameraDto.toCamera() = Camera(fullName = fullName, id = id, name = name, roverId = roverId)