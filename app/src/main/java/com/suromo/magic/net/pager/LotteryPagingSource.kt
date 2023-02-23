package com.suromo.magic.net.pager

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.suromo.magic.net.api.LotteryService
import com.suromo.magic.net.response.Lottery

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/24
 * desc   :
 */

private const val UNSPLASH_STARTING_YEAR_INDEX = 2023

class LotteryPagingSource(
    private val service: LotteryService,
    private val year: Int
) : PagingSource<Int, Lottery>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Lottery> {
        val page = params.key ?: UNSPLASH_STARTING_YEAR_INDEX
        return try {
            val response = service.getLotteryHistoryByYear(22, year)
            val lotteries = response.item
            Log.d("wxt","lotterieslotterieslotterieslotterieslotteries")
            Log.d("wxt", "load response:$response")
            LoadResult.Page(
                data = lotteries,
                prevKey = if (page == UNSPLASH_STARTING_YEAR_INDEX) null else page - 1,
                nextKey = if (page == 2024) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Lottery>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}