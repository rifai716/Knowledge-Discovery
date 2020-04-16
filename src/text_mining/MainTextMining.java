package text_mining;

import ALI.TextMiningLib;
import ALI.VectorLib;

import java.util.Map;

public class MainTextMining {
    public static void main(String[] args) {
        VectorLib vectorLib = new VectorLib();
        TextMiningLib textMiningLib = new TextMiningLib();

        String dir = "berita/";

        String[] docs = {"Berita1.txt","Berita2.txt","Berita3.txt"};


        /**
         * Important Variables
         */
        int n_docs = docs.length;
        String text;
        String[] words, keywords;
        Map[] map = new Map[n_docs];

        String query;
        String[] querylist;
        Map results;

        /**
         * TextMining Process
         */

        for (int i=0; i<n_docs; i++){
            text = textMiningLib.readFile(dir+docs[i]);
            // System.out.println(text);
            words = textMiningLib.Tokenizing(text);
            words = textMiningLib.Filtering(words);
            keywords = textMiningLib.StemmingTagging(words);
            map[i] = textMiningLib.Scoring(keywords);
        }

        textMiningLib.viewMap(map[0]);

        /**
         * Query Process
         */
        query = "Pembebasan napi";
//        query = "bem demo";
        querylist = textMiningLib.Tokenizing(query);
        querylist = textMiningLib.Filtering(querylist);
        querylist = textMiningLib.StemmingTagging(querylist);

        vectorLib.view(querylist);

        /**
         * Process Searching
         */
        results = textMiningLib.Search(querylist, map, docs);
        textMiningLib.viewMap(results);

        /**
         * Get Ranking Result and Scorring
         */

        String[] rankdocs = textMiningLib.getRetrievedDocs(results);
        int[] values = textMiningLib.getRetrievedValues(results);
        vectorLib.view(rankdocs);
        vectorLib.view(values);
    }
}
