package com.frank.types;
/********************************************************************************************
 * Represent criteria for search for an entry in a collection
 ********************************************************************************************/
public class AddamsSearchCriteria {
        private String  searchValue;
        private boolean isCaseSensitiveSearch;

        public AddamsSearchCriteria(String searchValue, boolean isCaseSensitiveSearch) {
                this.searchValue = searchValue;
                this.isCaseSensitiveSearch = isCaseSensitiveSearch;
        }

        public String getSearchValue() {
                return searchValue;
        }

        public boolean isCaseSensitiveSearch() {
                return isCaseSensitiveSearch;
        }
}
