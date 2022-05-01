/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.suromo.magic.ui.bean

/**
 * 获取发布的房源信息
 */
data class PostsFeed(
    val recommendedPosts: List<Post>,
    val otherPosts: List<Post>
) {
    /**
     * 获取所有发布的房源
     */
    val allPosts: List<Post> =
        recommendedPosts + otherPosts
}
