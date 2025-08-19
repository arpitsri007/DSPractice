package org.codekart.segmentTree;

public class MaxQuery {
    // leetcode 3479 - Fruits Into Baskets III

    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {

        BasketSegmentTree basketSegmentTree = new BasketSegmentTree(baskets);
        int unplacedFruits = 0;

        for (int i = 0; i < fruits.length; i++) {
            int fruitCount = fruits[i];
            if (!basketSegmentTree.occupyBasket(fruitCount, 0, 0, baskets.length - 1, basketSegmentTree.segmentTree)) {
                unplacedFruits++;
            }
        }
        return unplacedFruits;
    }

    class BasketSegmentTree {
        int[] segmentTree;
        int[] baskets;
        int n;

        public BasketSegmentTree(int[] baskets) {
            n = baskets.length;
            segmentTree = new int[4 * n];
            this.baskets = baskets;
            buildSegmentTree(baskets, segmentTree, 0, 0, n - 1);
        }

        public void buildSegmentTree(int[] baskets, int[] segmentTree, int index, int left, int right) {
            if (left == right) {
                segmentTree[index] = baskets[left];
                return;
            }
            int mid = (left + right) / 2;
            buildSegmentTree(baskets, segmentTree, 2 * index + 1, left, mid);
            buildSegmentTree(baskets, segmentTree, 2 * index + 2, mid + 1, right);
            segmentTree[index] = Math.max(segmentTree[2 * index + 1], segmentTree[2 * index + 2]);
        }

        public boolean occupyBasket(int fruitCount, int index, int left, int right, int[] segmentTree) {

            if (segmentTree[index] < fruitCount) {
                return false;
            }

            if (left == right) {
                segmentTree[index] = -1;
                return true;
            }

            int mid = (left + right) / 2;
            boolean canOccupy = false;
            if (segmentTree[2 * index + 1] >= fruitCount) {
                canOccupy = occupyBasket(fruitCount, 2 * index + 1, left, mid, segmentTree);
            } else {
                canOccupy = occupyBasket(fruitCount, 2 * index + 2, mid + 1, right, segmentTree);
            }
            segmentTree[index] = Math.max(segmentTree[2 * index + 1], segmentTree[2 * index + 2]);
            return canOccupy;
        }

    }
}
