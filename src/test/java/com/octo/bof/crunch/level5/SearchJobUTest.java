package com.octo.bof.crunch.level5;

import org.apache.crunch.PCollection;
import org.apache.crunch.impl.mem.collect.MemCollection;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Powered by o<+o (bjc).
 */

public class SearchJobUTest {

    @Test
    public void search_shouldReturnAnIterableOf_records_thatOnlyMatchTheSearchStr() throws Exception {
        // Given
        String searchStr = "merci";
        PCollection<InvertedIndexItem> records = new RecordsForTest()
                .add("salut", "Article-172")
                .add("merci", "Article-44")
                .add("bluck", "Article-22")
                .build();

        // When
        Iterable<InvertedIndexItem> result = new SearchJob().search(records, searchStr);

        // Then
        assertThat(result.iterator().next().docs).containsExactly("Article-44");
    }

    private class RecordsForTest extends ArrayList<InvertedIndexItem> implements List<InvertedIndexItem> {

        public RecordsForTest add(String word, String doc) {
            InvertedIndexItem item = new InvertedIndexItem();
            item.word = word;
            item.docs = new ArrayList<>();
            item.docs.add(doc);
            this.add(item);
            return this;
        }

        public PCollection<InvertedIndexItem> build() {
            return new MemCollection<InvertedIndexItem>(this);
        }
    }
}
