export class ArticleCreateModel{
    constructor(
        public articleTitle: string,
        public articleDescription: string,
        public articleType: string,
        public articlePrice: string
    ){ }
}