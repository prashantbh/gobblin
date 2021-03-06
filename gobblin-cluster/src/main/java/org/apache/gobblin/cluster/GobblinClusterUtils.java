/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.gobblin.cluster;

import static org.apache.gobblin.cluster.GobblinClusterConfigurationKeys.CLUSTRER_WORK_DIR;

import com.typesafe.config.Config;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.gobblin.annotation.Alpha;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

@Alpha
public class GobblinClusterUtils {

  /**
   * Get the name of the current host.
   *
   * @return the name of the current host
   * @throws UnknownHostException if the host name is unknown
   */
  public static String getHostname() throws UnknownHostException {
    return InetAddress.getLocalHost().getHostName();
  }

  /**
   * Get the application working directory {@link Path}.
   *
   * @param fs a {@link FileSystem} instance on which {@link FileSystem#getHomeDirectory()} is called
   *           to get the home directory of the {@link FileSystem} of the application working directory
   * @param applicationName the application name
   * @param applicationId the application ID in string form
   * @return the cluster application working directory {@link Path}
   */
  public static Path getAppWorkDirPath(FileSystem fs, String applicationName, String applicationId) {
    return new Path(fs.getHomeDirectory(), getAppWorkDirPath(applicationName, applicationId));
  }

  public static Path getAppWorkDirPathFromConfig(Config config, FileSystem fs,
      String applicationName, String applicationId) {
    if (config.hasPath(CLUSTRER_WORK_DIR)) {
      return new Path(config.getString(CLUSTRER_WORK_DIR));
    }
    return new Path(fs.getHomeDirectory(), getAppWorkDirPath(applicationName, applicationId));
  }

  /**
   * Get the application working directory {@link String}.
   *
   * @param applicationName the application name
   * @param applicationId the application ID in string form
   * @return the cluster application working directory {@link String}
   */
  public static String getAppWorkDirPath(String applicationName, String applicationId) {
    return applicationName + Path.SEPARATOR + applicationId;
  }
}
