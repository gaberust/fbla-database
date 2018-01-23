package grust.fbla;

import java.util.*;

/**
 * Class <code>SearchInfo</code> represents search results from a search
 * 
 * @author   GABE RUST - RAYMOND S. KELLIS FBLA/ESPORTS CLUB
 * @version  1.0
 * @since    May 1, 2016
 *
 */
public class SearchInfo {
	
	/**
	 * Public field <code>results</code>
	 * represents the search results of a search
	 */
	public FBLAMemberList results = new FBLAMemberList();
	
	/**
	 * Public field <code>indeces</code>
	 * represents the original indeces of the full Member List
	 */
	public ArrayList<Integer> indeces = new ArrayList<Integer>();
	
}