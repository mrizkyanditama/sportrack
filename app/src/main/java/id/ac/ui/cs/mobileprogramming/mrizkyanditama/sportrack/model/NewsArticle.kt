/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
data class Source(

  @field:Json(name = "id")
  val id: String? = null,

  @field:Json(name = "name")
  val name: String? = null
)

@JsonClass(generateAdapter = true)
data class NewsArticle(

  @field:Json(name = "source")
  val source: Source,

  /**
   * Name of the author for the article
   */
  @field:Json(name = "author")
  val author: String? = null,

  /**
   * Title of the article
   */
  @field:Json(name = "title")
  val title: String? = null,

  /**
   * Complete description of the article
   */
  @field:Json(name = "description")
  val description: String? = null,

  /**
   * URL to the article
   */
  @field:Json(name = "url")
  val url: String? = null,

  /**
   * URL of the artwork shown with article
   */
  @field:Json(name = "urlToImage")
  val urlToImage: String? = null,

  /**
   * Date-time when the article was published
   */
  @field:Json(name = "publishedAt")
  val publishedAt: String? = null,

  @field:Json(name = "content")
  val content: String? = null
)