package com.nasa.domain.utils



sealed class Resource<out T> {

  class Success<out T>(val value: T?) : Resource<T>()

  class Failure(
      val failureStatus: FailureStatus,
      val code: Int? = null,
      val message: String? = null
  ) : Resource<Nothing>()

  object Loading : Resource<Nothing>()

  object Default : Resource<Nothing>()

  fun isLoading() = this is Loading

  fun isError() = this is Failure

  fun toData() : T?= if(this is Success) this.value else null

  fun getError() : Failure? = if (this is Failure) this else null

  fun retrieveFailureStatus() : FailureStatus? = if (this is Failure) this.failureStatus else null

  fun isNetworkError() =  (this is Failure && this.failureStatus == FailureStatus.NO_INTERNET)

  fun isGeneralError() =  (this is Failure && this.failureStatus != FailureStatus.NO_INTERNET)
}