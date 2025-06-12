// src/app/model/article-model.ts

/** What your API returns for each article */
export interface ArticleModel {
    id: number | string;
    articleTitle: string;
    articleDescription: string;
    articleType: string;
    articlePrice: number;
    imageUrl?: string;
  }
  
  /** What you send when creating a new article */
  export class ArticleCreateModel {
    constructor(
      public articleTitle: string,
      public articleDescription: string,
      public articleType: string,
      public articlePrice: string
    ) {}
  }
  