/**
 * Copyright (c) @2014-10-20. All Rights Reserved.
 * AUTHOR: LIZHIWEI</a>
 */
package com.mapreduce.wordfrequency;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
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
public class WordsFrequenciesRunner extends Configured implements Tool {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		 int exitCode = ToolRunner.run(new WordsFrequenciesRunner(), args);

         System.exit(exitCode);

	}

	@Override
	public int run(String[] args) throws Exception {
		if (args.length < 2) {
            System.err.printf("Args missing. Input path and output path is required.");
            return -1;
       }
		
		Configuration conf = getConf();
		conf.setStrings("mapred.max.split.size", "1024");
		Job job = Job.getInstance(conf);
		
		 // 设置reducer读个数。每个reducer最终会产生一个输出文件
        job.setNumReduceTasks(2);
		job.setPartitionerClass(WordsFrequenciesPartitioner.class);
		job.setJobName("Calculate words frequencies");
		job.setJarByClass(getClass());
		
		FileInputFormat.addInputPaths(job, args[0]);
		
		Path outPath = new Path(args[1]);
		FileSystem fs = FileSystem.get(new URI(""), conf);
		if(fs.exists(outPath)) {
			fs.delete(outPath, true);
		}
		FileOutputFormat.setOutputPath(job, outPath);
		
		job.setMapperClass(WordsFrequenciesMapper.class);
		job.setReducerClass(WordsFrequenciesReducer.class);
		job.setCombinerClass(WordsFrequenciesCombiner.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		
	
		
		return job.waitForCompletion(true)?0:1;
	}

}
