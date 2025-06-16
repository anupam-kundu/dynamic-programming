package com.dp.practice;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint stopping you from
 * robbing each of them is that adjacent houses have security systems connected and it will
 * automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 *
 *
 * maximum sum sub sequence , considering 2 consecutive elements can not be selected
 *
 * M[i] = Max(M[i-2] + cost[i], M[i-1])
 * M[0] = cost[0]
 * M[1] = Max(cost[0], cost[1])
 *
 *
 *
 *
 * Problem changed to if house are arranged in circular array
 * then House[1] and House[n] are adjacent, they cannot be robbed together.
 * Therefore, the problem becomes to rob either House[1]-House[n-1] or House[2]-House[n],
 * depending on which choice offers more money.
 *
 * edge condition to be checked
 */
public class HouseRobber {
}
