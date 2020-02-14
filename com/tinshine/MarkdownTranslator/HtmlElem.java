package com.tinshine.MarkdownTranslator;

/**
 * HtmlElem
 */
public interface HtmlElem {
    String preProcess(String line);
    String toHtml(String line);
}