/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chubatiy.some.task;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author chubatiy
 */
public class MapNumbers {

    public Map<String, Integer> analyze(int[] numbers, int number, boolean canSort) {
        if (null == numbers) {
            return Collections.EMPTY_MAP;
        }
        int[] analyzed = canSort
                ? sorted(numbers, number)
                : unsorted(numbers, number);

        Map<String, Integer> result = new HashMap<>();
        result.put("above", analyzed[0]);
        result.put("below", analyzed[1]);
        return result;
    }

    private int[] unsorted(int[] numbers, int number) {
        //result
        int[] result = new int[2];
        //go to array check
        for (int i = 0; i < numbers.length; i++) {
            //inc
            if (numbers[i] > number) {
                result[0]++;
            } else {
                result[1]++;
            }
        }
        //return
        return result;
    }

    private int[] sorted(int[] numbers, int number) {
        //sort, it's a hack
        Arrays.sort(numbers);
        //used binary search
        int left = 0,
                right = numbers.length - 1,
                middle = 0;
        //while left != tight
        while (left <= right
                //and not founded our number
                && number != numbers[middle]) {
            //calculate mid
            middle = (right + left) / 2;
            //check value and modify left or right
            if (number < numbers[middle]) {
                right = middle - 1;
            } else if (number > numbers[middle]) {
                left = middle + 1;
            }
        }
        //return
        return new int[]{numbers.length - middle, middle};
    }

}