package com.holness.app;

public class IndexMinPriorityQueue<Key extends Comparable<Key>>  {
  private static final int MIN_INDEX = 1;
  private int maxCompacity;
  private int elementCount;
  private int[] pq;
  private int[] qp;
  private Key[] keys;

  public IndexMinPriorityQueue(int maxCompacity) {
    if (maxCompacity < 0) {
      throw new IllegalArgumentException();
    }
    this.maxCompacity = maxCompacity;
    this.elementCount = 0;
    setCompacity();
  }

  private void setCompacity() {
    pq = new int[maxCompacity + 1];
    qp = new int[maxCompacity + 1];
    setQPDefaults();
    keys = (Key[]) new Comparable[maxCompacity + 1];
  }

  private void setQPDefaults() {
    for (int i = 0; i <= maxCompacity; i++) {
      qp[i] = -1;
    }
  }

  public boolean isEmpty() {
    return elementCount == 0;
  }

  public boolean contains(int queueIndex) {
    return qp[queueIndex] != -1;
  }

  public void insert(int index, Key key) {
    checkIndex(index);
    this.elementCount++;
    addIndexedElement(index, key);
    swim(this.elementCount);
  }

  private void checkIndex(int index) {
    if (index < 0 || index >= maxCompacity) {
      throw new IndexOutOfBoundsException();
    } else if (contains(index)) {
      throw new IllegalArgumentException("index is already in queue");
    }
  }

  private void addIndexedElement(int index, Key key) {
    this.qp[index] = elementCount;
    this.pq[this.elementCount] = index;
    this.keys[index] = key;
  }

  public Key minKey() {
    return this.keys[pq[1]];
  }

  public int minIndex() {
    return this.pq[1];
  }

  private void swim(int queueIndex) {
    while (queueIndex > 1 && greaterThanParent(queueIndex / 2, queueIndex)) {
      int parentIndex = queueIndex / 2;
      exchangeIndexes(queueIndex, parentIndex);
      queueIndex = parentIndex;
    }
  }

  private boolean greaterThanParent(int parentIndex, int childIndex) {
    Key par = keys[pq[parentIndex]];
    Key child = keys[pq[childIndex]];
    return par.compareTo(child) > 0;
  }

  private void exchangeIndexes(int intialIndex, int swapIndex) {
    int placeholder = pq[intialIndex];
    pq[intialIndex] = pq[swapIndex];
    pq[swapIndex] = placeholder;
    qp[pq[intialIndex]] = intialIndex;
    qp[pq[swapIndex]] = swapIndex;
  }

  public Key keyOf(int index) {
    return keys[index];
  }

  public void decreaseKey(int index, Key lowerKey) {
    keys[index] = lowerKey;
    swim(qp[index]);
  }

  public int delMin() {
    int min = pq[MIN_INDEX];
    exchangeIndexes(MIN_INDEX, this.elementCount--);
    sink(MIN_INDEX);
    removeMinRef(min);
    return min;
  }

  private void removeMinRef(int min) {
    qp[min] = -1;
    keys[min] = null;
    pq[this.elementCount + 1] = -1;
  }

  private void sink(int keyIndex) {
    while (2 * keyIndex <= this.elementCount) {
      int childIndex = 2 * keyIndex;
      if (childIndex < this.elementCount && greaterThanParent(childIndex, childIndex + 1)) {
        childIndex++;
      }
      if (!greaterThanParent(keyIndex, childIndex)) {
        break;
      }
      exchangeIndexes(keyIndex, childIndex);
      keyIndex = childIndex;
    }
  }
}
