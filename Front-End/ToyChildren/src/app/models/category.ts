export class Category {
  id: number;
  name: string;
  parentId: number;
  childrens : Array<Category>;
}
