/*
 * Copyright 2022 The Kindling Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.kindling.agent.profiler;

import io.kindling.agent.deps.one.profiler.AsyncProfiler;
import io.kindling.agent.deps.one.profiler.Events;
import io.kindling.agent.util.FileWriter;

public class AsyncProfilerStarter {
	public static void start(AsyncProfiler instance, long interval, int depth, FileWriter writer) throws Exception {
		try {
			writer.log("Check CPU...");
			executeCommand(instance, "check", Events.CPU, interval, depth, writer);
			writer.log("Start CPU...");
			executeCommand(instance, "start", Events.CPU, interval, depth, writer);
		} catch (IllegalStateException ex) {
			try {
			    writer.log("CPU Not Supported, Cause: " + ex.getMessage() + ", Use alluser instead.");
			    executeCommand(instance, "check,alluser", Events.CPU, interval, depth, writer);
				executeCommand(instance, "start,alluser", Events.CPU, interval, depth, writer);
			} catch (IllegalStateException alluserEx) {
			    writer.log("CPU alluser Not Supported, Cause: " + alluserEx.getMessage() + ", Use itimer instead.");
			    executeCommand(instance, "start", Events.ITIMER, interval, depth, writer);
			}
		}
	}
	
	
	private static String executeCommand(AsyncProfiler instance, String command, String event, long interval, int depth, FileWriter writer) throws Exception {
		return instance.execute(command + ",event=" + event + ",interval=" + interval + "m,jstackdepth=" + depth + ",event=" + Events.LOCK + ",threads");
	}
}
