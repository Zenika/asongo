/*
 * The MIT License (MIT)
 *
 * Copyright (c)  2014 Zenika
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
