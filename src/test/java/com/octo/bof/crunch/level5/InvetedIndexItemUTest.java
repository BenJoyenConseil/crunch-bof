package com.octo.bof.crunch.level5;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Powered by o<+o (bjc).
 */

public class InvetedIndexItemUTest {

    @Test
    public void toString_NominalCase() throws Exception {
        // Given
        InvertedIndexItem invertedIndexItem = new InvertedIndexItem();
        invertedIndexItem.word = "bluck";
        invertedIndexItem.docs = new ArrayList<String>();
        invertedIndexItem.docs.add("art1");
        invertedIndexItem.docs.add("art2");

        // When
        String result = invertedIndexItem.toString();

        // Then
        assertThat(result).isEqualTo("bluck | art1,art2,");
    }
}
