/*
 * Copyright 2014 Zenika
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author M. Labusquière
 */

package com.zenika.vertx.lib.asongo.domain;

import java.util.Map;

/**
 * Representation of a DB stats
 * @author M. Labusquière
 */
public class MongoStats {

	private String serverUsed;
	private String ns;
	private int	count;
	private int size;
	private double avgObjSize;
	private long storageSize;
	private int	numExtents;
	private int nindexes;
	private int lastExtentSize;
	private double paddingFactor;
	private int flags;
	private long totalIndexSize;
	private Map<String, Integer> indexSizes;

	public MongoStats()	{}

	public String getServerUsed() {
		return serverUsed;
	}

	public void setServerUsed(String serverUsed) {
		this.serverUsed = serverUsed;
	}

	public String getNs() {
		return ns;
	}

	public void setNs(String ns) {
		this.ns = ns;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public double getAvgObjSize() {
		return avgObjSize;
	}

	public void setAvgObjSize(double avgObjSize) {
		this.avgObjSize = avgObjSize;
	}

	public long getStorageSize() {
		return storageSize;
	}

	public void setStorageSize(long storageSize) {
		this.storageSize = storageSize;
	}

	public int getNumExtents() {
		return numExtents;
	}

	public void setNumExtents(int numExtents) {
		this.numExtents = numExtents;
	}

	public int getNindexes() {
		return nindexes;
	}

	public void setNindexes(int nindexes) {
		this.nindexes = nindexes;
	}

	public int getLastExtentSize() {
		return lastExtentSize;
	}

	public void setLastExtentSize(int lastExtentSize) {
		this.lastExtentSize = lastExtentSize;
	}

	public double getPaddingFactor() {
		return paddingFactor;
	}

	public void setPaddingFactor(double paddingFactor) {
		this.paddingFactor = paddingFactor;
	}

	public int getFlags() {
		return flags;
	}

	public void setFlags(int flags) {
		this.flags = flags;
	}

	public long getTotalIndexSize() {
		return totalIndexSize;
	}

	public void setTotalIndexSize(long totalIndexSize) {
		this.totalIndexSize = totalIndexSize;
	}

	public Map<String, Integer> getIndexSizes() {
		return indexSizes;
	}

	public void setIndexSizes(Map<String, Integer> indexSizes) {
		this.indexSizes = indexSizes;
	}

	@Override
	public String toString() {
		return "MongoStats{" +
				"serverUsed='" + serverUsed + '\'' +
				", ns='" + ns + '\'' +
				", count=" + count +
				", size=" + size +
				", avgObjSize=" + avgObjSize +
				", storageSize=" + storageSize +
				", numExtents=" + numExtents +
				", nindexes=" + nindexes +
				", lastExtentSize=" + lastExtentSize +
				", paddingFactor=" + paddingFactor +
				", flags=" + flags +
				", totalIndexSize=" + totalIndexSize +
				", indexSizes=" + indexSizes +
				'}';
	}
}
