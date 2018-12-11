package com.bibtexparser;

public enum RecordType {
    ARTICLE {
        public String[] getRequired() {
            String[] req = new String[]{"author", "title", "journal", "year"};
            return req;
        }

        public String[] getOptional() {
            String[] opt = new String[]{"volume", "number", "pages", "month", "note", "key"};
            return opt;
        }
    },
    BOOK {
        public String[] getRequired() {
            String[] req = new String[]{"author/editor", "title", "publisher", "year"};
            return req;
        }

        public String[] getOptional() {
            String[] opt = new String[]{"volume", "series", "address", "edition", "month", "note", "key"};
            return opt;
        }
    },
    INPROCEEDINGS {
        public String[] getRequired() {
            String[] req = new String[]{"author", "title", "booktitle", "year"};
            return req;
        }

        public String[] getOptional() {
            String[] opt = new String[]{"editor", "volume/number", "series", "pages", "address", "month", "organization", "publisher",  "note", "key"};
            return opt;
        }
    },
    BOOKLET {
        public String[] getRequired() {
            String[] req = new String[]{"title",};
            return req;
        }

        public String[] getOptional() {
            String[] opt = new String[]{"author", "howpublished", "address", "month", "year", "note", "key"};
            return opt;
        }
    },
    INBOOK {
        public String[] getRequired() {
            String[] req = new String[]{"author/editor", "title", "chapter/pages", "publisher", "year"};
            return req;
        }

        public String[] getOptional() {
            String[] opt = new String[]{"volume/number", "series", "type", "address", "edition", "month", "note", "key"};
            return opt;
        }
    },
    INCOLLECTION {
        public String[] getRequired() {
            String[] req = new String[]{"author", "title", "booktitle", "publisher", "year"};
            return req;
        }

        public String[] getOptional() {
            String[] opt = new String[]{"editor", "volume/number", "series", "type", "chapter", "pages", "address", "edition", "month", "note", "key"};
            return opt;
        }
    },
    MANUAL {
        public String[] getRequired() {
            String[] req = new String[]{"title"};
            return req;
        }

        public String[] getOptional() {
            String[] opt = new String[]{"author", "organization", "address", "edition", "month", "year", "note", "key"};
            return opt;
        }
    },
    MASTERTHESIS {
        public String[] getRequired() {
            String[] req = new String[]{"author", "title", "school", "year"};
            return req;
        }

        public String[] getOptional() {
            String[] opt = new String[]{"type", "address", "month", "note", "key"};
            return opt;
        }
    },
    PHDTHESIS {
        public String[] getRequired() {
            String[] req = new String[]{"author", "title", "school", "year"};
            return req;
        }

        public String[] getOptional() {
            String[] opt = new String[]{"type", "address", "month", "note", "key"};
            return opt;
        }
    },
    TECHREPORT {
        public String[] getRequired() {
            String[] req = new String[]{"author", "title", "institution", "year"};
            return req;
        }

        public String[] getOptional() {
            String[] opt = new String[]{"editor", "volume/number", "series", "address", "month", "organization", "publisher", "note", "key"};
            return opt;
        }
    },
    MISC {
        public String[] getRequired() {
            String[] req = new String[]{};
            return req;
        }

        public String[] getOptional() {
            String[] opt = new String[]{"author", "title", "howpublished", "month", "year", "note", "key"};
            return opt;
        }
    },
    UNPUBLISHED {
        public String[] getRequired() {
            String[] req = new String[]{"author", "title", "note"};
            return req;
        }

        public String[] getOptional() {
            String[] opt = new String[]{"month", "year", "key"};
            return opt;
        }
    };

    public abstract String[] getRequired();
    public abstract String[] getOptional();
}
