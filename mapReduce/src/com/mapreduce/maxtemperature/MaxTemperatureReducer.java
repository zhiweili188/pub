/**
 * Copyright (c) @2014-10-20. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.mapreduce.maxtemperature;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-10-20
 * @Version: 1.0
 */
public class MaxTemperatureReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	Log log = LogFactory.getLog(this.getClass());
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,  Context context) throws IOException, InterruptedException {
		 int maxValue = Integer.MIN_VALUE;
		
		 for(IntWritable value : values) {
			 log.info("value:["+value.get()+"]");
			 maxValue = Math.max(value.get(), maxValue);
		 }
		 
		 context.write(key	, new IntWritable(maxValue));
	}

}
