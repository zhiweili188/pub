/**
 * Copyright (c) @2014-10-20. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.mapreduce.maxtemperature;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @Description:
 * @author lizhiwei
 * @Date: 2014-10-20
 * @Version: 1.0
 */
public class MaxTemperatureDriver extends Configured implements Tool {
	@Override
	public int run(String[] args) throws Exception {
		if(args.length != 2) {
			System.err.printf("Usage: %s <input> <output>", getClass().getSimpleName());
			ToolRunner.printGenericCommandUsage(System.err);
			return -1;
		}
		
		Configuration conf = getConf();
		Job job = new Job(conf, "Max Temperature");
		job.setJarByClass(getClass());
		
		FileInputFormat.addInputPaths(job, args[0]);
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(MaxTemperatureMapper.class);
		job.setReducerClass(MaxTemperatureReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		return job.waitForCompletion(true)?0:1;
	}

	public static void main(String[] args) throws Exception {
		int status = ToolRunner.run(new MaxTemperatureDriver(), args);
		System.exit(status);
	}
}
