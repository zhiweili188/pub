/**
 * Copyright (c) @2014-10-20. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.mapreduce.wordfrequency;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-10-20
 * @Version: 1.0
 */
public class WordsFrequenciesPartitioner extends Partitioner<Text, LongWritable> {

	@Override
	public int getPartition(Text key, LongWritable value, int numOfReducer) {
		return key.toString().length()%numOfReducer;
	}

}
