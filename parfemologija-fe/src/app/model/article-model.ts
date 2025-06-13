// src/app/model/article-model.ts

/** What your API returns for each article */
export interface ArticleModel {
    id: number;
    userId: number;
    articleTitle: string;
    articleDescription: string;
    articleType: string;
    articlePrice: number;
    thumbnailImageUrl?: string;
    createdAt: string;
    articleLocation: string;
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
  