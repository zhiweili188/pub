/**
 * Copyright (c) @2014-10-20. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.mapreduce.wordfrequency;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-10-20
 * @Version: 1.0
 */
public class WordsFrequenciesMapper extends Mapper<LongWritable, Text, Text, LongWritable>{

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		 String line = value.toString().trim();
		   if (line.length() == 0)  {
	              return;
	         }
		   String[] wordStrings = line.split("\\s+");
		  String tmp = null;
		   for (String word : wordStrings) {
			   tmp = word.toLowerCase();
			   tmp = tmp.replaceAll("[\\.,\"`]", "");
			   tmp = tmp.trim();
			 
			   context.write(new Text(tmp), new LongWritable(1));
		   }
	}

}
