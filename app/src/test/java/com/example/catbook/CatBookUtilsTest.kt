package com.example.catbook

import com.example.catbook.data.local.CatBreedData
import com.example.catbook.data.local.CatImageData
import com.example.catbook.data.remote.CatBreedRemoteData
import com.example.catbook.data.remote.CatImageRemoteData
import com.example.catbook.utils.getCatBreedEpoxyData
import com.example.catbook.utils.getCatImageEpoxyData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

internal class CatBookUtilsTest {

    @Test
    fun `getCatBreedEpoxyData with valid input returns expected output`() {
        val remoteData = listOf(
            CatBreedRemoteData(
                id = "1",
                name = "Abyssinian",
                temperament = "Curious, active",
                origin = "India"
            ),
            CatBreedRemoteData(
                id = "2",
                name = "Aegean",
                temperament = "Affectionate, Social, Intelligent, Playful, Active",
                origin = "Greece"
            ),
            CatBreedRemoteData(
                id = "3",
                name = "American Curl",
                temperament = "Affectionate, Curious, Intelligent, Interactive, Lively, Playful, Social",
                origin = "United States"
            ),
        )

        val epoxyData = remoteData.getCatBreedEpoxyData()

        val expectedEpoxyData = listOf(
            CatBreedData(
                id = "1",
                breedName = "Abyssinian",
                origin = "India",
                temperament = "Curious, active"
            ),
            CatBreedData(
                id = "2",
                breedName = "Aegean",
                origin = "Greece",
                temperament = "Affectionate, Social, Intelligent, Playful, Active"
            ),
            CatBreedData(
                id = "3",
                breedName = "American Curl",
                origin = "United States",
                temperament = "Affectionate, Curious, Intelligent, Interactive, Lively, Playful, Social"
            ),
        )
        assertEquals(expectedEpoxyData, epoxyData)
    }

    @Test
    fun `getCatBreedEpoxyData with null input returns empty list`() {

        val remoteData: List<CatBreedRemoteData>? = null
        val epoxyData = remoteData.getCatBreedEpoxyData()
        assertTrue(epoxyData.isEmpty())
    }

    @Test
    fun `getCatBreedEpoxyData maps null values to empty strings`() {
        val remoteData = listOf(
            CatBreedRemoteData(
                id = "1",
                name = "Abyssinian",
                temperament = null,
                origin = null
            ),
            CatBreedRemoteData(
                id = "2",
                name = null,
                temperament = "Affectionate, Social, Intelligent, Playful, Active",
                origin = "Greece"
            ),
        )
        val epoxyData = remoteData.getCatBreedEpoxyData()
        val expectedEpoxyData = listOf(
            CatBreedData(
                id = "1",
                breedName = "Abyssinian",
                origin = "",
                temperament = ""
            ),
            CatBreedData(
                id = "2",
                breedName = "",
                origin = "Greece",
                temperament = "Affectionate, Social, Intelligent, Playful, Active"
            ),
        )
        assertEquals(expectedEpoxyData, epoxyData)
    }

    @Test
    fun `getCatBreedEpoxyData with empty input returns empty list`() {
        val remoteData = emptyList<CatBreedRemoteData>()
        val epoxyData = remoteData.getCatBreedEpoxyData()
        assertTrue(epoxyData.isEmpty())
    }

    @Test
    fun `getCatImageEpoxyData with valid input returns expected output`() {
        val remoteData = listOf(
            CatImageRemoteData(
                id = "1",
                url = "https://cdn.example.com/cat1.jpg",
                breeds = listOf(
                    CatBreedRemoteData(
                        id = "3",
                        name = "American Curl",
                        temperament = "Affectionate, Curious, Intelligent, Interactive, Lively, Playful, Social",
                        origin = "United States"
                    )
                )
            ),
            CatImageRemoteData(
                id = "2",
                url = "https://cdn.example.com/cat2.jpg",
                breeds = listOf(
                    CatBreedRemoteData(
                        id = "2",
                        name = "Aegean",
                        temperament = "Affectionate, Social, Intelligent, Playful, Active",
                        origin = "Greece"
                    )
                )
            )
        )
        val epoxyData = remoteData.getCatImageEpoxyData()
        val expectedEpoxyData = listOf(
            CatImageData(
                id = "1",
                breedName = "American Curl",
                imageUrl = "https://cdn.example.com/cat1.jpg"
            ),
            CatImageData(
                id = "2",
                breedName = "Aegean",
                imageUrl = "https://cdn.example.com/cat2.jpg"
            )
        )
        assertEquals(expectedEpoxyData, epoxyData)
    }

    @Test
    fun `getCatImageEpoxyData with null input returns empty list`() {
        val remoteData: List<CatImageRemoteData>? = null
        val epoxyData = remoteData.getCatImageEpoxyData()
        assertTrue(epoxyData.isEmpty())
    }

    @Test
    fun `getCatImageEpoxyData with empty input returns empty list`() {
        val remoteData = emptyList<CatImageRemoteData>()
        val epoxyData = remoteData.getCatImageEpoxyData()
        assertTrue(epoxyData.isEmpty())
    }

    @Test
    fun `getCatImageEpoxyData maps null values to default values`() {
        val remoteData = listOf(
            CatImageRemoteData(
                id = "1",
                url = "https://cdn.example.com/cat1.jpg",
                breeds = null,
            ),
            CatImageRemoteData(
                id = "2",
                url = "https://cdn.example.com/cat2.jpg",
                breeds = listOf()
            )
        )
        val epoxyData = remoteData.getCatImageEpoxyData()
        val expectedEpoxyData = listOf(
            CatImageData(
                id = "1",
                breedName = "Breed Not Present",
                imageUrl = "https://cdn.example.com/cat1.jpg"
            ),
            CatImageData(
                id = "2",
                breedName = "Breed Not Present",
                imageUrl = "https://cdn.example.com/cat2.jpg"
            )
        )
        assertEquals(expectedEpoxyData, epoxyData)
    }

    @Test
    fun `getCatImageEpoxyData with missing breed name uses default value`() {
        val remoteData = listOf(
            CatImageRemoteData(
                id = "1",
                url = "https://cdn.example.com/cat1.jpg",
                breeds = listOf(
                    CatBreedRemoteData(
                        id = "3",
                        name = null,
                        temperament = "Affectionate, Curious, Intelligent, Interactive, Lively, Playful, Social",
                        origin = "United States"
                    )
                )
            ),
            CatImageRemoteData(
                id = "2",
                url = "https://cdn.example.com/cat2.jpg",
                breeds = listOf(
                    CatBreedRemoteData(
                        id = "2",
                        name = "",
                        temperament = "Affectionate, Social, Intelligent, Playful, Active",
                        origin = "Greece"
                    )
                )
            )
        )
        val epoxyData = remoteData.getCatImageEpoxyData()
        val expectedEpoxyData = listOf(
            CatImageData(
                id = "1",
                breedName = "Breed Not Present",
                imageUrl = "https://cdn.example.com/cat1.jpg"
            ),
            CatImageData(
                id = "2",
                breedName = "",
                imageUrl = "https://cdn.example.com/cat2.jpg"
            )
        )
        assertEquals(expectedEpoxyData, epoxyData)
    }


}