package lecture14.after;

import com.sun.source.tree.ReturnTree;
import lecture14.businesslogic.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

    public class TwitterFeed {

        private ArrayList<Post> feed;
        private HashMap<String, Integer> trendingTopics;

        public TwitterFeed(){
            this.feed = new ArrayList<>();
            this.trendingTopics = new HashMap<>();
        }

        public void add(Post post){
            this.feed.add(post);
        }

        public void checkTrendingTopics(){
            // Go through each post
            for(Post post : this.feed){
                //Retrieve the hashtags in that post. Then, for each Hashtag:
                //  Check if the hashtag was mentioned before (it would exist as a key in the map).
                //     If yes, then retrieve the number of times mentioned, increment and put back in map.
                //     If not, then add the hashtag to the map as a key with value 1 (mentioned once).
                HashSet<String> hashtags = post.getHashtags();
                for(String hashtag : hashtags){
                    if(  trendingTopics.containsKey(hashtag) ){
                        int numOfMention = trendingTopics.get(hashtag);
                        numOfMention = numOfMention + 1;
                        trendingTopics.put(hashtag, numOfMention);
                    } else {
                        trendingTopics.put(hashtag, 1);
                    }
                }
            }
        }

        public HashMap<String, Integer> getTopics(){
            return this.trendingTopics;
        }


        public String toString(){
            final String END_OF_LINE = System.lineSeparator();
            String allPosts = "";
            for(Post currentPost : this.feed){
                allPosts = allPosts + currentPost.getPostContent() + END_OF_LINE;
            }
            return allPosts;
        }

    }

}
