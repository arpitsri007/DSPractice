package org.codekart.recursion;

public class Cookies {

    int result = Integer.MAX_VALUE;

    public int distributeCookies(int[] cookies, int k) {
        int[] children = new int[k];
        distributeCookiesHelper(cookies, 0, k, children);
        return result;
    }

    private void distributeCookiesHelper(int[] cookies, int idx, int k, int[] children) {
        if (idx >= cookies.length) {
            result = Math.min(result, getMax(children));
            return;
        }

        int cookie = cookies[idx];

        for (int i = 0; i < k; i++) {
            children[i] += cookie;
            distributeCookiesHelper(cookies, idx + 1, k, children);
            children[i] -= cookie;
        }
    }

    private int getMax(int[] children) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < children.length; i++) {
            max = Math.max(max, children[i]);
        }
        return max;
    }
}
