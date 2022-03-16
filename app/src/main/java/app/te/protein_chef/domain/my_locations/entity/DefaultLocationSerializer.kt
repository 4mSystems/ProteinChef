package app.te.protein_chef.domain.my_locations.entity

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.structure.base_mvvm.DefaultLocation
import java.io.InputStream
import java.io.OutputStream

object DefaultLocationSerializer: Serializer<DefaultLocation> {
  override val defaultValue: DefaultLocation = DefaultLocation.getDefaultInstance()
  override suspend fun readFrom(input: InputStream): DefaultLocation {
    try {
      return DefaultLocation.parseFrom(input)
    } catch (exception: InvalidProtocolBufferException) {
      throw CorruptionException("Cannot read proto.", exception)
    }
  }

  override suspend fun writeTo(t: DefaultLocation, output: OutputStream) = t.writeTo(output)
}
